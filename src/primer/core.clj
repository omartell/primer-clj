(ns primer.core
  (:require [clojure.string :as string]))

(defn primes
  "Generate a lazy sequence of prime numbers. As we already know
   that 2, 3, 5 are prime numbers skip checking their multiples by
   adding a wheel of increments"
  ([] (conj (primes [2 3 5] 7 (cycle [4 2 4 2 4 6 2 6]))
            5 3 2))
  ([collected n increments]
   (if (some #(zero? (mod n %)) collected)
     (lazy-seq (primes collected
                       (+ (first increments) n)
                       (rest increments)))
     (cons n (lazy-seq (primes
                        (conj collected n)
                        (+ (first increments) n)
                        (rest increments)))))))

(defn multiplication-table [numbers]
  "Given a sequence of numbers return its multiplication table"
  (partition (count numbers) (for [x numbers
                                   y numbers]
                               (* x y))))

(defn pad-right
  "Returns a new string of a specified length in which the end of
  the passed in string is padded with spaces"
  ([length] (pad-right length ""))
  ([length string]
   (apply str (take length
                    (concat (seq (str string)) (repeat " "))))))

(defn print-multiplication-table [primes table]
  "Format and print the multiplication table"
  (let [cell-length (-> table last last str count)
        cell (partial pad-right cell-length)
        header (string/join " " (conj (map cell primes) (cell "|") (cell "N")))
        dashes (apply str (take (count header) (repeat "-")))]
    (println header)
    (println dashes)
    (doseq [row (map conj table (repeat "|") primes)]
      (println (string/join " " (map cell row))))))

(defn -main [& args]
  (let [n (Integer/parseInt (first args))
        selected-primes (take n (primes))
        rows (multiplication-table selected-primes)]
    (print-multiplication-table selected-primes rows)))
