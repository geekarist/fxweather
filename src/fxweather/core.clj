(ns fxweather.core
  (:gen-class
   :extends javafx.application.Application)
  (:import
   (javafx.application Application)
   (javafx.fxml FXMLLoader)
   (javafx.scene Parent Scene)
   (javafx.stage Stage))
  (:require [clojure.java.io :as io]))

(defn -main []
  (try (Application/launch fxweather.core (into-array String []))
       (catch Exception e
         (do (.printStackTrace e)
             (throw e)))))

(defn -start [_this primaryStage]
  (try (let [loc (io/resource "ui.fxml")
             root (FXMLLoader/load ^java.net.URL loc)
             scene (Scene. root 300 250)]
         (.setScene primaryStage scene)
         (.show primaryStage))
       (catch Exception e
         (do (.printStackTrace e)
             (throw e)))))

(comment
  (-main))