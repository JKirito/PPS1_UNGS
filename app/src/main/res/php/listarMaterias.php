<?php

mysql_connect("127.0.0.1","root","1234");
mysql_select_db("guiame");

$sql=mysql_query("SELECT m.nombre, c.comision, h.horaInicio, h.horaFin, p.nombre FROM profesores p, materias m, cursos c, horarios h, cursos_horarios ch,  cursos_profesores cp WHERE ch.id_cursos = c.id AND ch.id_horarios = h.id AND c.id_materia = m.id AND cp.id_cursos = c.id AND cp.id_profesores = p.id");

while($row=mysql_fetch_assoc($sql))
$output[]=$row;
print(json_encode($output));
mysql_close();
?>