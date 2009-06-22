@echo off
echo RESET WateOn Database!!
echo Are you sure?!
echo ..
pause

echo Input root's password of MySQL to DROP WateOn Database
mysql -uroot -p < drop_db.sql
echo Database is dropped.

echo Input root's password of MySQL to CREATE WateOn Database
mysql -uroot -p < create_db.sql
echo Database is created.

mysql -uwateon -p1234 < create_table.sql
echo Tables are created.
