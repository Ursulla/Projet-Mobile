/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;


import java.util.List;
import java.util.Map;

import dao.Dao;
import model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseService{
     @Autowired
	 private Dao dao;
	 
	 public Dao getDao() {
	     return this.dao;
	 }
	 
	 public void setDao(Dao dao) {
	     this.dao = dao;
	 }
	 
	 public void insert(BaseModel baseModel) {
		 getDao().insert(baseModel);
	 }
	 
	 public void update(BaseModel baseModel) {
		 getDao().update(baseModel);
	 }
	 
	 public void saveOrUpdate(BaseModel baseModel) {
		 getDao().saveOrUpdate(baseModel);
	 }
	 
	 public void delete(BaseModel baseModel,int id) {
		 getDao().delete(baseModel,id);
	 }
	 
	 public void delete(BaseModel baseModel) {
		 getDao().delete(baseModel);
	 }
	 
	 public void deleteAll(BaseModel baseModel) {
		 getDao().deleteAll(baseModel);
	 }
	 
	 public BaseModel getById(BaseModel baseModel, int id) {
		 return getDao().getById(baseModel, id);
	 }
	 
	 public List<BaseModel> getByParameters(BaseModel baseModel, Map<String, String> params) {
		 return getDao().getByParameters(baseModel, params);
	 }
	 
	 public List<BaseModel> search(BaseModel baseModel, Map<String, String> params) {
		 return getDao().search(baseModel, params);
	 }
	 
	 public List<BaseModel> getAll(BaseModel baseModel) {
		 return getDao().getAll(baseModel);
	 }
	 
	 public List<BaseModel> getAllWithPagination(BaseModel baseModel, Integer firstResult, Integer maxResult, String orderByParam, Boolean asc) {
		 return getDao().getAllWithPagination(baseModel,firstResult,maxResult,orderByParam,asc);
	 }
	 
	 public int count(BaseModel baseModel) {
		 return getDao().count(baseModel);
	 }
	 
//	 public List<BaseModel> GroupBy(BaseModel b, Map<String, String> params, String max, String groupBy) {
//		 return getDao().GroupBy(b,params,max,groupBy);
//	 }
	 
}

