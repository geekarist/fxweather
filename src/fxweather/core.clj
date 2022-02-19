(ns fxweather.core
  (:gen-class :extends javafx.application.Application)
  (:import (javafx.application Application Platform)
           (javafx.fxml FXMLLoader)
           (javafx.scene #_Parent Scene)
           #_(javafx.stage Stage))
  (:require [clojure.java.io :as io]))

(defn- catchall [command]
  (try (command)
       (catch Exception e
         (do (.printStackTrace e)
             (throw e)))))

(defn -main []
  (catchall #(Application/launch fxweather.core (into-array String []))))

(defn- create-scene! []
  (let [loc (io/resource "ui.fxml")
        root (FXMLLoader/load ^java.net.URL loc)
        scene (Scene. root)]
    scene))

(defn -start [_this primaryStage]
  #_{:clj-kondo/ignore [:inline-def]}
  (def MAIN_STAGE primaryStage)
  (catchall #(do (.setScene primaryStage (create-scene!))
                 (.show primaryStage))))

(defn reload-ui! []
  (Platform/runLater
   (fn []
     (catchall #(do (.hide MAIN_STAGE)
                    (.setScene MAIN_STAGE (create-scene!))
                    (.show MAIN_STAGE))))))

(comment
  (comment -start)
  (future (-main))
  (future (reload-ui!))
  )