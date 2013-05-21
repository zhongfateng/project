package  com.nbw.tupu.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.nbw.common.util.tupu.CommonsFiend;
import com.nbw.common.util.tupu.FileFiend;
import com.nbw.common.util.tupu.PathUtil;
import com.nbw.tupu.dao.FmptTpDAO;
import com.nbw.tupu.domain.FmptTp;
import com.nbw.tupu.domain.Item;

/**
 * 
 * FmptBioclassManager
 * 
 * @author 
 * 
 */
public class FmptTpManager{

	private FmptTpDAO fmpttpdao;
	
	private static Logger logger = Logger.getLogger(FmptTpManager.class);

	private static String ENCODE = "UTF-8";
/**
 * 从ftp上读取数据
 * @param name
 * @return
 *//*
	public Item search(String name) {
		Item item = new Item();
		item.setName(name);
		String data = FileFiend.readFile("test.json", ENCODE);
		String webRoot = PathUtil.getWebRoot();
		String jsonUrl = "data/" + CommonsFiend.getRandomString(20) + ".search.json";
		FileFiend.writeFile(webRoot + "/" + jsonUrl, data, ENCODE);
		item.setJsonUrl(jsonUrl);
		return item;
	}*/
	/**
	 * 读取数据
	 * @param name
	 * @return
	 * @throws FileNotFoundException 
	 */
		public Item search(String tpid) throws FileNotFoundException {
		Item item = new Item();
		String filename = this.findById(tpid).getAscii();
		//item.setName(filename.substring(0, filename.indexOf(".")));
		item.setName(filename);
		String fileDirectory = "d:/downloadFile/tupu/"; // 设定上存档案目录
		File fileout = new File(fileDirectory + filename);
		InputStream in = new FileInputStream(fileout);
		String jsonUrl = "data/" + CommonsFiend.getRandomString(20)
				+ ".search.json";
		item.setJsonUrl(jsonUrl);
		String txtUrl="txt/"+filename;
		String data = FileFiend.readFile(in, ENCODE);
		String webRoot = PathUtil.getWebRoot();
		FileFiend.writeFile(webRoot + "/" + jsonUrl, format(data), ENCODE);
		FileFiend.writeFile(webRoot + "/" + txtUrl, data, ENCODE);
		return item;
		}
		
	public Item saveFile(FileItem fileItem) {
		Item item = new Item();
		String jsonUrl = "data/" + CommonsFiend.getRandomString(20) + ".update.json";
		item.setName(fileItem.getName());
		item.setJsonUrl(jsonUrl);
		logger.info("上传文件的大小:" + fileItem.getSize());
		logger.info("上传文件的类型:" + fileItem.getContentType());
		logger.info("上传文件的名称:" + fileItem.getName());
		try {
			String data = FileFiend.readFile(fileItem.getInputStream(), ENCODE);
			String webRoot = PathUtil.getWebRoot();
			FileFiend.writeFile(webRoot + "/" + jsonUrl, format(data), ENCODE);
		} catch (IOException e) {
			logger.error(e, e);
			item = null;
		}
		return item;
	}

	
	
	public  String saveTxt(FileItem fileItem)
	{
		String txtUrl="txt/"+fileItem.getName();
		String filepath=null;
		try {
			String data = FileFiend.readFile(fileItem.getInputStream(), ENCODE);
			String webRoot = PathUtil.getWebRoot();
			filepath=webRoot + "/" + txtUrl;
			FileFiend.writeFile(filepath, data, ENCODE);
		} catch (IOException e) {
			logger.error(e, e);
		
		}
		return filepath;
		
		
		
	}
	private String format(String data) {
		String format = null;
		StringBuffer buffer = new StringBuffer();
		boolean flag = false;
		if (data != null) {
			buffer.append("[");
			String[] datas = data.split("\r\n");
			for (int i = 0; i < datas.length; i++) {
				String[] items = datas[i].trim().split("\\s");
				if (items.length == 2) {
					if (flag)
						buffer.append(",");
					buffer.append("[").append(items[0]).append(",")
							.append(items[1]).append("]");
					flag = true;
				}
			}
			buffer.append("]");
			format = buffer.toString();
		}
		return format;
	}
	/**
	 * 通过id得到对象
	 *
	 * @param  id
	 * @return pObject
	 */
	public FmptTp findById(String id){
		if(id==null||"".equals(id)){
		   return null;
		}else{
			FmptTp pObject = fmpttpdao.findByID(id);
		   return pObject;
		}		
	}
	/**
	 * 获得该对象所有数据的列表
	 * 
	 * @return list
	 */
	public List getAll(){
		return fmpttpdao.findAll();
	}
	
    /**
	 * 保存对象
	 *
	 * @param  fmptbioclass
	 */
	public void saveFmptTp(FmptTp fmpttp) {
		fmpttpdao.save(fmpttp);
	}


    /**
	 * 编辑对象
	 *
	 * @param  fmptbioclass
	 */
	public void editFmptTp(FmptTp fmpttp) {
		fmpttpdao.update(fmpttp);
	}
	
	/**
	 * 删除对象
	 *
	 * @param  id
	 */
	public void deleteFmptTp(String id) {
		fmpttpdao.delete(id);
	}
	/**
	 * 根据用户输入的名字搜索
	 * @param name
	 * @return
	 */
	public List<FmptTp> findByName(String name){
		return fmpttpdao.findByName(name);
	}
	public int findBySpid(Integer spid){
		return fmpttpdao.findBySpid(spid);
	}
	
	public FmptTpDAO getFmpttpdao() {
		return fmpttpdao;
	}

	public void setFmpttpdao(FmptTpDAO fmpttpdao) {
		this.fmpttpdao = fmpttpdao;
	}
	
}

