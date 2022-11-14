SELECT * FROM `likelion-db`.nation_wide_hospitals WHERE road_name_address LIKE "경기도 수원시%" AND hospital_name LIKE "%피부과%";
SELECT * FROM `likelion-db`.nation_wide_hospitals WHERE business_type_name IN ('보건진료소', '보건소', '보건지소') AND road_name_address LIKE "%서울특별시 성북구%";
