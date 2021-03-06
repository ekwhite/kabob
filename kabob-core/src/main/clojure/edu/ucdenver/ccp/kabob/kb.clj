(ns edu.ucdenver.ccp.kabob.kb
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sesame.kb
       edu.ucdenver.ccp.kabob.namespace)
  (import org.openrdf.rio.RDFFormat
          org.openrdf.query.resultio.TupleQueryResultFormat))

;;; --------------------------------------------------------
;;; kabob source kbs
;;; --------------------------------------------------------

(defn binary-kb []
  (let [kb (kb org.openrdf.repository.http.HTTPRepository)]
    (.setPreferredRDFFormat (:server kb) RDFFormat/BINARY)
    (.setPreferredTupleQueryResultFormat (:server kb)
                                         TupleQueryResultFormat/BINARY)
    (register-namespaces (synch-ns-mappings (connection kb))
                         *namespaces*)))

(defn open-kb [args]
  ;; Init source KB connection
  (binding [*default-server* (:server-url args)
            *repository-name* (:repo-name args)
            *username* (:username args)
            *password* (:password args)]
    (binary-kb)))


