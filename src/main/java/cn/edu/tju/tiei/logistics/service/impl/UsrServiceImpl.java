package cn.edu.tju.tiei.logistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tju.tiei.logistics.dao.UsrMapper;
import cn.edu.tju.tiei.logistics.model.Usr;
import cn.edu.tju.tiei.logistics.service.IUsrService;

@Service("usrService")
public class UsrServiceImpl implements IUsrService {

	@Resource
	private UsrMapper usrMapper;

	public UsrMapper getUsrMapper() {
		return usrMapper;
	}

	@Autowired
	public void setUsrMapper(UsrMapper usrMapper) {
		this.usrMapper = usrMapper;
	}

	public List<Usr> findAll() {
		return usrMapper.selectByExample(null);
	}

	public Usr findById(String id) {
		return usrMapper.selectByPrimaryKey(id);
	}

	public void create(Usr usr) {
		usrMapper.insert(usr);
	}

	public boolean isExist(Usr usr) {
		return usrMapper.selectByPrimaryKey(usr.getId()) != null;
	}

	public void update(Usr usr) {
		usrMapper.updateByPrimaryKey(usr);
	}

	public void deleteById(String id) {
		usrMapper.deleteByPrimaryKey(id);
	}

	public void deleteAll() {
		usrMapper.deleteByExample(null);
	}

}
