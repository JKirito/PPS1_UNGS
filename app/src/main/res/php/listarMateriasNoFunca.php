<?php

//mysql_connect("","root","1234");
$db = new mysqli('127.0.0.1', 'root', '1234', 'guiame');

if($db->connect_errno > 0){
    die('Unable to connect to database [' . $db->connect_error . ']');
}
//mysql_select_db("guiame");


$sql = <<<SQL
    SELECT m.materia as 'materia', c.comision, h.horaInicio, h.horaFin, p.nombre FROM materias m, cursos c, horarios h, cursos_horarios ch, profesores p, cursos_profesores cp
WHERE ch.id_cursos = c.id AND 
ch.id_horarios = h.id AND 
c.id_materia = m.id AND 
cp.id_cursos = c.id AND
cp.id_profesores = p.id 
SQL;

if(!$result = $db->query($sql)){
    die('There was an error running the query [' . $db->error . ']');
}



while(($row = $result->fetch_assoc())
//$output[]=$row;
print(json_encode($output));
echo 'Total results: ' . $result->num_rows;
echo mysql_error();
$db->close();

?>