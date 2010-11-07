cd ..

TESTA="java -classpath lib/easyaccept.jar:./tests/:. easyaccept.EasyAccept "
cd build/br/edu/ufcg/dsc/si/blog/webservice/

$TESTA BlogaoWSImpl.class tests/us1.txt;
$TESTA BlogaoWSImpl.class tests/us2.txt;
$TESTA BlogaoWSImpl.class tests/us3.txt;
$TESTA BlogaoWSImpl.class tests/us4.txt;
$TESTA BlogaoWSImpl.class tests/us5.txt;
$TESTA BlogaoWSImpl.class tests/us6.txt;
$TESTA BlogaoWSImpl.class tests/us7.txt;
$TESTA BlogaoWSImpl.class tests/us8.txt;
$TESTA BlogaoWSImpl.class tests/us9.txt;
$TESTA BlogaoWSImpl.class tests/us10.txt;
$TESTA BlogaoWSImpl.class tests/us11.txt;
$TESTA BlogaoWSImpl.class tests/us12.txt;
$TESTA BlogaoWSImpl.class tests/us13.txt;
$TESTA BlogaoWSImpl.class tests/us14.txt;

