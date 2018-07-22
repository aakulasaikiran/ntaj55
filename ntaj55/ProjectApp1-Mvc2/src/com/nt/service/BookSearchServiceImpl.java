package com.nt.service;

import java.util.ArrayList;
import java.util.List;

import com.nt.bo.BookBO;
import com.nt.dao.BookSearchDAO;
import com.nt.dao.BookSearchDAOImpl;
import com.nt.dto.BookDTO;

public class BookSearchServiceImpl implements BookSearchService {

	@Override
	public List<BookDTO> search(String category)throws Exception {
		BookSearchDAO dao=null;
		List<BookBO> listBO=null;
		List<BookDTO> listDTO=null;
		BookDTO dto=null;
		//use DAO
		dao=new BookSearchDAOImpl();
		listBO=dao.findBooks(category);
		//copy ListBO to ListDTO
		listDTO=new ArrayList();
		for(BookBO bo:listBO){
			dto=new BookDTO();
			dto.setBookId(bo.getBookId());
			dto.setBookName(bo.getBookName());
			dto.setAuthor(bo.getAuthor());
			dto.setStatus(bo.getStatus());
			dto.setCategory(bo.getCategory());
			listDTO.add(dto);
		}//for
		return listDTO;
	}//method
}//class
