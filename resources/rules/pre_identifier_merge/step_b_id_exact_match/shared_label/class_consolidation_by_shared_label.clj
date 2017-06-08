;; ------------------------------------------------------------
;; ---------  relation consolidation via shared label ---------
;; ------------------------------------------------------------
`{:name          "class-consolidation-by-shared_label"
  :title         "creates exactMatch mappings between Class identifiers whose classes share the same exact label"
  :head          ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?c1 ?c2 ?label {
                       ?c1 oboInOwl:hasOBONamespace ?ns1 .
                       ?c1 rdf:type owl:Class .
                       ?c1 rdfs:label ?label .
                       ?c2 rdfs:label ?label .
                       ?c2 oboInOwl:hasOBONamespace ?ns2 .
                       ?c2 rdf:type owl:Class .
                       # require that c1 and c2 are different and return in a reproducible order to avoid duplicate pairings
                       # also exclude several ontologies:
                       #   PR: so that we don't intentionally collapse genes with proteins
                       #   NCBITaxon: matches appear to be spurious
                       #   UBERON: so as not to incorrectly collapse species-specific anatomy
                       #   PO: also so as not to incorrectly collapse plant anatomy with other things
                       FILTER (?c1 != ?c2 && STR(IRI(?c1)) < STR(IRI(?c2))
                                   && !contains(str(?c1), 'obo/PR_')
                                   && !contains(str(?c2), 'obo/PR_')
                                   && !contains(str(?c1), 'obo/UBERON_')
                                   && !contains(str(?c2), 'obo/UBERON_')
                                   && !contains(str(?c1), 'obo/NCBITaxon_')
                                   && !contains(str(?c2), 'obo/NCBITaxon_')
                                   && !contains(str(?c1), 'obo/PO_')
                                   && !contains(str(?c2), 'obo/PO_')
                                   # CHEBI:base and SO:base are not the same
                                   && ?label != 'base'
                                   && ?label != 'probe'
                                   && ?label != 'direct'
                                   )
                       ?id1 obo:IAO_0000219 ?c1 .
                       ?id2 obo:IAO_0000219 ?c2 .
                       # exclude any links between different GGPV identifiers
                       minus {?id1 rdfs:subClassOf* IAO_EXT_0000083}  # ccp:gene_or_gene_product_or_variant_identifier
                       minus {?id2 rdfs:subClassOf* IAO_EXT_0000083}  # ccp:gene_or_gene_product_or_variant_identifier
                       }"
  }
