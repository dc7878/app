// IBookManager.aidl
package com.cc.dc.common.bean;
import com.cc.dc.common.bean.Book;

interface IBookManager {

    List<Book> loadAll();
}
