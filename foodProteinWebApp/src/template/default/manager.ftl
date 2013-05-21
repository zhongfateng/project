package  ${moduleName}.service;

import java.util.List;

import ${moduleName}.dao.${daoName};
import ${longDomainClass};

/**
 * 
 * ${managerName}
 * 
 * @author 
 * 
 */
public class ${managerName}{

	private ${daoName} ${daoName?lower_case};


	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public ${shortDomainClass} findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
		   ${shortDomainClass} pObject = ${daoName?lower_case}.findByID(id);
		   return pObject;
		}		
	}
	
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return ${daoName?lower_case}.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  ${shortDomainClass?lower_case}
	 */
	public void save${shortDomainClass}(${shortDomainClass} ${shortDomainClass?lower_case}) {
		${daoName?lower_case}.save(${shortDomainClass?lower_case});
	}


    /**
	 * 编辑对象
	 *
	 * @param  ${shortDomainClass?lower_case}
	 */
	public void edit${shortDomainClass}(${shortDomainClass} ${shortDomainClass?lower_case}) {
		${daoName?lower_case}.update(${shortDomainClass?lower_case});
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void delete${shortDomainClass}(String id) {
		${daoName?lower_case}.delete(id);
	}


	
	//*******************************************************************************set and get
	public ${daoName} get${daoName?lower_case?cap_first}() {
		return ${daoName?lower_case};
	}

	public void set${daoName?lower_case?cap_first}(${daoName} ${daoName?lower_case}) {
		this.${daoName?lower_case} = ${daoName?lower_case};
	}

}
