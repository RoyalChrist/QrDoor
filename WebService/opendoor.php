<?php
/**
 * Created by PhpStorm.
 * User: yanhanxuan
 * Date: 2016/4/16
 * Time: 20:19
 */
header("Content-Type: text/html;charset=utf-8");
$con = mysqli_connect("192.168.19.49", "root", "roots704", "door");

$userId = $_GET['userId'];
$code = $_GET['code'];
$randNumber = $_GET['randNumber'];

//查询扫描二维码的用户名
$sqlUserName = "SELECT * from user WHERE _id='$userId'";
$resultUserName = mysqli_query($con, $sqlUserName);
$arrayUserName = mysqli_fetch_array($resultUserName);
$userName = $arrayUserName["loginName"];
$realName = $arrayUserName["realName"];


$sqlDoorId = "SELECT doorId from qrcode WHERE code='$code' AND randnumber='$randNumber' AND isUsed=0";
$resultDoorId = mysqli_query($con, $sqlDoorId);
if (mysqli_num_rows($resultDoorId) != 0) {
    $arrayDoorId = mysqli_fetch_array($resultDoorId);
    $doorId = $arrayDoorId["doorId"];
    //通过doorId查询对应门的信息
    $sqlDoor = "SELECT * from door WHERE _id='$doorId'";
    $resultDoor = mysqli_query($con, $sqlDoor);
    $arrayDoor = mysqli_fetch_array($resultDoor);
    //查到doorNumber
    $doorNumber = $arrayDoor["doorNumber"];
    //查到openCode
    $openCode = $arrayDoor["openCode"];
    //查到ip
    $ip = $arrayDoor["ip"];
    //查到port
    $port = $arrayDoor["port"];
    //查到nodeNumber
    $nodeNumber = $arrayDoor["nodeNumber"];
    //查到关门代码
    $close = "#CLOS#0".$nodeNumber."$";
    //通过doorId查询userId
    $sqlUserId = "SELECT userId from relationship WHERE doorId='$doorId'";
    $resultUserId = mysqli_query($con, $sqlUserId);
    while ($arrayUserId = mysqli_fetch_array($resultUserId)) {
        if ($arrayUserId["userId"] == $userId)
            break;
    }
    if ($arrayUserId["userId"] != "") {
        sendsock($ip, $port, $openCode);
        sleep(1);
        sendsock($ip, $port, $close);
        $response["open"] = 1;
        //将扫描二维码的用户名插入数据库
        mysqli_query($con,"UPDATE qrcode SET userScan='$userName' WHERE doorId = '$doorId' AND isUsed=0");
        //标记二维码过期
        mysqli_query($con,"UPDATE qrcode SET isUsed='1' WHERE doorId = '$doorId' AND isUsed=0");
        //插入开门记录
        mysqli_query($con,"INSERT INTO log(doorNumber,loginName,realName,record,time) values ('$doorNumber','$userName','$realName','开门',(now()));");
    } else {
        $response["open"] = 0;
    }
} else {
    $response["open"] = -1;
}
echo json_encode($response);
mysqli_close($con);

function sendsock($address, $service_port, $in)
{

    $socket = socket_create(AF_INET, SOCK_STREAM, SOL_TCP);
    /*if ($socket === false) {
        echo "socket_create() failed: reason: " . socket_strerror(socket_last_error()) . "\n";
    } else {
        echo "OK. \n";
    }
    echo "Attempting to connect to '$address' on port '$service_port'...";*/
    $result = socket_connect($socket, $address, $service_port);
    /* if ($result === false) {
         echo "socket_connect() failed.\nReason: ($result) " . socket_strerror(socket_last_error($socket)) . "\n";
     } else {
         echo "OK \n";
     }
     $out = "";
     echo "sending http head request ...";*/
    socket_write($socket, $in, strlen($in));
    //echo "OK\n";
    socket_close($socket);
    //echo "ok .\n\n";
}