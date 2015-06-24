------------------------------------------------------------------objInfo---------------------------------------------------
insert into af_obj_info(id,name,type,params,removed) select seq_af_obj_info.nextval,'KPI指标库','kpiInfo','{"entity":"com.wonders.frame.kpi.model.bo.KpiInfo"}',0 from dual;
insert into af_obj_info(id,name,type,params,removed) select seq_af_obj_info.nextval,'KPI指标考核','kpiAssess','{"entity":"com.wonders.frame.kpi.model.bo.KpiAssess"}',0 from dual;
insert into af_obj_info(id,name,type,params,removed) select seq_af_obj_info.nextval,'KPI指标考核数据','kpiData','{"entity":"com.wonders.frame.kpi.model.bo.KpiDataInfo"}',0 from dual;
insert into af_obj_info(id,name,type,params,removed) select seq_af_obj_info.nextval,'KPI自评表','self','{"entity":"com.wonders.frame.kpi.model.bo.SelfEvaluation"}',0 from dual;
insert into af_obj_info(id,name,type,params,removed) select seq_af_obj_info.nextval,'KPI指标历史','kpiInfoHistory','{"entity":"com.wonders.frame.kpi.model.bo.KpiInfoHistory"}',0 from dual;
																											
------------------------------------------------------------------KpiInfo---------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='kpiBusinessType');
delete from af_ccate where type='kpiBusinessType';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'kpiBusinessType','业务类型','kpi业务类型字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'运营生产类','运营生产类' ,1 from af_ccate where type='kpiBusinessType';

------------------------------------------------------------------kpiType---------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='kpiType');
delete from af_ccate where type='kpiType';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'kpiType','指标分类','指标分类字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'财务效益','财务效益' ,1 from af_ccate where type='kpiType';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'生产安全','生产安全' ,2 from af_ccate where type='kpiType';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'服务质量','服务质量' ,3 from af_ccate where type='kpiType';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'设备设施','设备设施' ,4 from af_ccate where type='kpiType';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'重点工作','重点工作' ,5 from af_ccate where type='kpiType';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'基础管理','基础管理' ,6 from af_ccate where type='kpiType';

------------------------------------------------------------------考核部门---------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='kpiAssessDept');
delete from af_ccate where type='kpiAssessDept';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'kpiAssessDept','考核部门','考核部门字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'财务部','财务部' ,1 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'运管部','运管部' ,2 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'节能办','节能办' ,3 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'安监室','安监室' ,4 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'保卫部','保卫部' ,5 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'设施设备部','设施设备部' ,6 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'各相关部门','各相关部门' ,7 from af_ccate where type='kpiAssessDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'合约部','合约部' ,8 from af_ccate where type='kpiAssessDept';
------------------------------------------------------------------被考核部门---------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='kpiDept');
delete from af_ccate where type='kpiDept';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'kpiDept','考核公司','考核公司字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海轨道交通运营管理中心','上海轨道交通运营管理中心' ,1 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海地铁维护保障有限公司','上海地铁维护保障有限公司' ,2 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海地铁第一运营有限公司','上海地铁第一运营有限公司' ,3 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海地铁第二运营有限公司','上海地铁第二运营有限公司' ,4 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海地铁第三运营有限公司','上海地铁第三运营有限公司' ,5 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海地铁第四运营有限公司','上海地铁第四运营有限公司' ,6 from af_ccate where type='kpiDept';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'上海磁浮交通发展有限公司','上海磁浮交通发展有限公司' ,7 from af_ccate where type='kpiDept';

------------------------------------------------------------------year---------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='year');
delete from af_ccate where type='year';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'year','启用年份','启用年份字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,2015,'2015','2015' ,1 from af_ccate where type='year';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,2014,'2014','2014' ,2 from af_ccate where type='year';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,2013,'2013','2013' ,3 from af_ccate where type='year';
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,2012,'2012','2012' ,4 from af_ccate where type='year';

--------------------------------------------------系统数据来源-------------------------------------------------------------------
delete from af_codes where ccate_id in(select id from af_ccate where type='kpiDataSource');
delete from af_ccate where type='kpiDataSource';
insert into af_ccate(id,type,name,description) select seq_af_ccate.nextval,'kpiDataSource','系统数据来源','系统数据来源字典' from dual;
insert into af_codes(id,ccate_id,code,display,description,orders) select seq_af_codes.nextval,id,seq_af_codes.currval,'协同平台','协同平台' ,1 from af_ccate where type='kpiDataSource';

