(ns primer.core-test
  (:require [clojure.test :refer :all]
            [primer.core :refer :all]))

(deftest prime-numbers-sequence
  (is (= [2 3 5 7 11 13 17 19 23 29]
         (take 10 (primes))))
  (is (= 7919 (nth (primes) 999))))

(deftest padding-strings
  (is (= "1    " (pad-right 5 "1"))))

(deftest multiplication-tables
  (is (= [[1 2 3] [2 4 6] [3 6 9]]
         (multiplication-table [1 2 3]))))
