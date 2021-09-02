package com.GridService;

import java.util.List;

import com.GridDao.GridDao;
import com.been.GridModel;

public class GridService {

	private GridDao dao= new GridDao();
	public List<GridModel> getdata(int rows, int page) {
		
		return dao.getdata(rows,page);
	}

}
