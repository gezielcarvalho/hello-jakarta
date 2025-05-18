#!/bin/bash

echo "🔁 Watching for changes (java, jsp, xml)..."

watchexec -r -e java,jsp,xml,html,css,js,xhtml \
  --ignore target \
  --ignore .git \
  -- ./rebuild.sh