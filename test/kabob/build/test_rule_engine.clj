(ns kabob.build.test-rule-engine
  (use clojure.test
       clojure.pprint

       kr.core.utils
       kr.core.rule
       kabob.core.rule

       kabob.core.namespace)
  (require ;;kabob.core.kabob
           clojure.string))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

;;(defonce ^:dynamic *rcon* nil)

;;(def rule-test-path "resources/rules/pre-identifier-merge/step_a-ontology_to_ice/step_a/")

;;; --------------------------------------------------------
;;; functions
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; manual / live tests
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; automated tests
;;; --------------------------------------------------------

;;this is to correct for some combination of dependencies in the poms
;;  generating a state of being that prevents the classpath from being
;;  seen by the clojure code.
(deftest verify-classpath-readable
  ;;(pprint (classpath-seq))
  (is (< 5 (count (classpath-seq)))))

;;(deftest verify-specific-rule-file-readable
;;  (is (< 1 
;;         (count 
;;          (kabob.core.rule/kabob-load-rules-from-classpath
;;           rule-test-path)))))



;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------