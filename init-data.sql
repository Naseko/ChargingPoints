insert into CUSTOMERS (id,name,number) values (100,'Mike','e165653d-054c-4b18-a2cd-b182861578e1');
insert into VEHICLES (id,name,reg_plate) values (200,'Some car-100','e165253d-054c-4b18-a2cd-b182861578e1');
insert into CHARGING_POINTS (id,title,usn,customer_id) values (300,'Test Point','357bb1ee-08b1-44e0-865b-85fb91b7f0b3',100);
insert into RFIDS (id,name,number,customer_id,vehicle_id) values (400,'Some rfid','0de04a7c-de5e-469e-a58b-559aeed16ad8',100,200);