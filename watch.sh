#!/bin/bash
echo "Watching for file changes to auto-redeploy..."

watchexec -r -e java,jsp,xml,html,css,js --ignore target --ignore .git -- mvn compile war:exploded
