(ns fxweather.core
  (:gen-class
   :extends javafx.application.Application)
  (:import
   (javafx.application Application Platform)
   (javafx.fxml FXMLLoader)
   (javafx.scene #_Parent Scene)
   #_(javafx.stage Stage))
  (:require [clojure.java.io :as io]))

(defn -main []
  (try (Application/launch fxweather.core (into-array String []))
       (catch Exception e
         (do (.printStackTrace e)
             (throw e)))))

(defn- create-scene! []
  (let [loc (io/resource "ui.fxml")
        root (FXMLLoader/load ^java.net.URL loc)
        scene (Scene. root)]
    scene))

(defn -start [_this primaryStage]
  #_{:clj-kondo/ignore [:inline-def]}
  (def MAIN_STAGE primaryStage)
  (try
    (.setScene primaryStage (create-scene!))
    (.show primaryStage)

    (catch Exception e
      (do (.printStackTrace e)
          (throw e)))))

(defn reload-ui! []
  (Platform/runLater
   #(try (.hide MAIN_STAGE)
         (.setScene MAIN_STAGE (create-scene!))
         (.show MAIN_STAGE)
         (catch Exception e
           (do (.printStackTrace e)
               (throw e))))))

(comment
  (comment -start)
  (future (-main))
  (future (reload-ui!))
  )