echo `pwd`
T=`pwd`
cd "$T/build"
echo `pwd`

export TESTA="java -classpath lib/easyaccept.jar:./tests/:. easyaccept.EasyAccept "


$TESTA facadesTests.ws.BlogaoWSImpl tests/us1.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us2.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us3.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us4.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us5.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us6.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us7.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us8.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us9.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us10.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us11.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us12.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us13.txt;
$TESTA facadesTests.ws.BlogaoWSImpl tests/us14.txt;

