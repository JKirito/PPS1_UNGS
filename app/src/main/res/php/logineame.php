<?php
//  echo 'HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAA';


  mysql_connect("127.0.0.1","root","1234");
  mysql_select_db("personas");
  $sql=mysql_query("select * from usuarios");
  while($row=mysql_fetch_assoc($sql))
	$output[]=$row;
  print(json_encode($output));
  mysql_close();
?>
