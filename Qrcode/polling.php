<?php
$con = mysqli_connect("192.168.19.49", "root", "roots704", "door");
$secret = $_GET['secret'];
$result = mysqli_query($con,"SELECT * FROM qrcode WHERE randnumber='$secret'");
$row = mysqli_fetch_array($result);
if ($row['userScan'] != "") {
    echo "true";
} else {
    echo "false";
}