<html>
<head>
    <meta http-equiv="refresh" content="30;url=#">
    <style>
        body {
            background-color: black;
        }
    </style>
</head>
<body>
<div style="margin-left: 500px;margin-top: 150px">
    <?php
    if (empty($_REQUEST['doorid'])) {
        die('NO door ID');
    }
    require 'mysql_connect.php';
    $con = mysqli_connect("192.168.19.49", "root", "roots704", "door");
    $sql = "select * from door where _id=?";
    $stmt = $mysqli->stmt_init();
    $stmt = $mysqli->prepare($sql);
    $id = intval($_REQUEST['doorid']);
    $stmt->bind_param("i", $id);
    $stmt->execute();
    $result = $stmt->get_result();
    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
    $doornumber = $row['doorNumber'];
    //将超时时间存入session
    $secret = md5(rand() . time());
    $img_id = $doornumber . '_' . $secret;

    //存入数据库
    $sql = "insert into qrcode(doorid,code,randnumber,createtime,exptime,isused) values (?,?,?,(now()),ADDDATE(now(),interval 30 second),0);";
    $stmt = $mysqli->stmt_init();
    $stmt = $mysqli->prepare($sql);
    $stmt->bind_param("iss", $id, $doornumber, $secret);
    $stmt->execute();
    //30秒后将二维码设置为过期
    $mresult = mysqli_query($con, "SELECT * FROM qrcode WHERE code='$doornumber' ORDER BY _id DESC LIMIT 1,1");
    $arraymresult = mysqli_fetch_array($mresult);
    $mresult_id = $arraymresult["_id"];
    mysqli_query($con, "UPDATE qrcode SET isUsed='1' WHERE _id='$mresult_id'");

    /* */
    $PNG_TEMP_DIR = dirname(__FILE__) . DIRECTORY_SEPARATOR . 'temp' . DIRECTORY_SEPARATOR;
    //html PNG location prefix
    $PNG_WEB_DIR = 'temp/';
    include "qrlib.php";
    //ofcourse we need rights to create temp dir
    if (!file_exists($PNG_TEMP_DIR)) mkdir($PNG_TEMP_DIR);
    $filename = $PNG_TEMP_DIR . 'test.png';

    $errorCorrectionLevel = 'M';
    if (isset($_REQUEST['level']) && in_array($_REQUEST['level'], array(
            'L',
            'M',
            'Q',
            'H'
        ))
    ) $errorCorrectionLevel = $_REQUEST['level'];
    $matrixPointSize = 4;
    if (isset($_REQUEST['size'])) $matrixPointSize = min(max((int)$_REQUEST['size'], 1), 10);
    if (isset($img_id)) {
        if (trim($img_id) == '') die('data cannot be empty! <a href="?">back</a>');
        $filename = $PNG_TEMP_DIR . 'test' . md5($img_id . '|' . $errorCorrectionLevel . '|' . $matrixPointSize) . '.png';
        QRcode::png($img_id, $filename, $errorCorrectionLevel, $matrixPointSize, 2);
    }
    //display generated file
    echo '<img src="' . $PNG_WEB_DIR . basename($filename) . '" width="400p" height="400px" />';
    // benchmark
    //QRtools::timeBenchmark();
    ?>
</div>
<input hidden="hidden" type="text" name="secret" id="secret" value="<?php echo $secret; ?>"/>
</body>
<script>
    function polling() {
        //执行轮询数据库
        var xmlHttp;
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        } else {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.status == 200 && xmlHttp.readyState == 4) {
                result = xmlHttp.responseText;
                if (result == 'true') {
                    window.location.reload();
                }
            }
        }
        secret = document.getElementById("secret").value;
        xmlHttp.open("GET", "polling.php?secret=" + secret, true);
        xmlHttp.send();
    }
    setInterval("polling()", 1000);
</script>
</html>
