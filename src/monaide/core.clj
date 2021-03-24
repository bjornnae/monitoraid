(ns monaide.core
  (:require [clojure.string :as sstr] [clojure.java.io :as jio])
  
  (:gen-class))

(defn- get-file-age-millis 
  "Wrapper function for java.io.File.lastModified. Returning age in milliseconds of the file resource."
  [filepath]
  (let [now (.toEpochMilli (java.time.Instant/now)) 
        ]
    (- now (.lastModified (jio/file filepath))))
)

(defn- millis-to-days [milliseconds]
  (/ milliseconds 1000 60 60 24))

(defn- days-to-millis [days]
  (* days 24  60 60 1000))


(defn check-if-older-millis
  "Checks if file in filepath has a last modified attribute which is greater than max-age-milliseconds. 
   Returns true (is older) or false (is not older) .
   N.B. Directories always pass this test even if they are too old.
   "
  [filepath max-age-milliseconds]
 (if (not (.isDirectory (jio/file filepath)))
  (if (> (get-file-age-millis filepath) max-age-milliseconds)
    true
    false)
   false
   )
)


(defn debug-check-if-older-millis [filepath max-age-milliseconds]
  (println "DEBUG-18: filepath: " filepath " 
        max-age-days: " (millis-to-days max-age-milliseconds) "                                                         
        max-age-milliseconds: " max-age-milliseconds "                                   
        is milliseconds:      "
           (get-file-age-millis filepath)
           "
        is too old: " (check-if-older-millis filepath max-age-milliseconds))
  (check-if-older-millis filepath max-age-milliseconds))

(defn check-if-older-days
  "Check if a file in filepath is older than max-days."
  [filepath max-days]
  (check-if-older-millis  filepath (days-to-millis max-days)))

(defn- wifwrap
  "Wrapper function for running a list alst with (filepath max-file-age) pairs through the check-if-older function."
  [alst]
  [(first alst) (check-if-older-millis (first alst) (last alst))])


(defn- batremover-line
  "Produce a windows batch file which removes too old files if you run it. 
   Takes a list with (filepath true/false) pairs which is the output from check-directory or check-if-older."
  [alst]
   (sstr/join "\n" (map #(if (last %) (str "delete " (first %)) "") alst)))


(defn check-directory
  "Checks all files in dir if older than max-age-days.
   Returns a list '(filenpath Boolean)."
  [dir max-age-days]
  (let [directory (jio/file dir)
        files (file-seq directory)]
    (map #(list (.getAbsolutePath %) (check-if-older-days % max-age-days)) files)))

(defn- wrap-check-directory
  [alist]
  (apply check-directory alist))


(defn -main [& args] ; Get command line arguments
  (if-not (empty? args)
    (doseq [arg args]
      (print  "REM list of files to be deleted."
                "\nREM config file:" arg))
     ; In case there are no command line arguments
    (throw (Exception. "Expects file path to configuration file.")))
  (let [config-filepath (first args)
        config-lines (sstr/split-lines (slurp config-filepath))
        largs (map #(list (first (sstr/split % #" "))
                          (Integer/parseInt (last (sstr/split % #" ")))) config-lines)]
    (def f (map wrap-check-directory largs))
     (println (reduce str "" (map batremover-line f)))
     )
)