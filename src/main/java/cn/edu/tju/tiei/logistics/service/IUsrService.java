package cn.edu.tju.tiei.logistics.service;

import java.util.List;

import cn.edu.tju.tiei.logistics.model.Usr;

public interface IUsrService {

    /**
     * Load all usrs
     * @return
     */
    List<Usr> findAll();

    Usr findById(String id);

	void create(Usr usr);

	boolean isExist(Usr usr);

	void update(Usr usr);

	void deleteById(String id);

	void deleteAll();

}
