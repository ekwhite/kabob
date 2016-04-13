#!/bin/bash

#NOTE: due to CD'ing in script use absolute file names!!
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

EXPECTED_ARGS=2

if [ $# -ne $EXPECTED_ARGS ]
then
    echo "#NOTE: due to CD'ing in script use absolute file names!!"
    echo "Usage: LOG_FILE TARGET_DOWNLOAD_DIR"
    echo "current usage:"
    echo $@
    exit 1
fi

LOG_FILE=$1
TARGET_DIR=$2

echo "Logging to: $LOG_FILE"
echo "Saving files to: $TARGET_DIR"

URLS="http://purl.obolibrary.org/obo/bfo.owl
http://purl.obolibrary.org/obo/chebi.owl
http://purl.obolibrary.org/obo/cl.owl
http://purl.obolibrary.org/obo/doid.owl
http://geneontology.org/ontology/extensions/go-plus.owl
http://purl.obolibrary.org/obo/iao.owl
http://purl.obolibrary.org/obo/ncbitaxon.owl
http://www.openannotation.org/spec/core/20130208/oa.owl
http://purl.obolibrary.org/obo/oba.owl
http://purl.obofoundry.org/obo/obi.owl
http://purl.obolibrary.org/obo/pato.owl
http://purl.obolibrary.org/obo/po.owl
http://purl.obolibrary.org/obo/pr.owl
http://purl.obolibrary.org/obo/ro.owl
http://purl.obolibrary.org/obo/so.owl
http://purl.obolibrary.org/obo/uberon/ext.owl
http://purl.obolibrary.org/obo/mi.owl"

exit_code=0
for url in $URLS
do
  echo "downloading $url"
  $DIR/download-and-log.sh $LOG_FILE $TARGET_DIR $url
  e=$?
  if [ $e -ne 0 ]; then
    exit_code=$e
  fi
done

exit $exit_code




