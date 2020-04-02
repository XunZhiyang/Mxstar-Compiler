# this script is called when the judge is building your compiler.
# no argument will be passed in.
echo "AAA"
set -e
cd "$(dirname "$0")"
echo "$0"
mkdir -p bin
javac -cp "./lib/antlr-4.8-complete.jar" -d bin $(find src -name "*.java")
