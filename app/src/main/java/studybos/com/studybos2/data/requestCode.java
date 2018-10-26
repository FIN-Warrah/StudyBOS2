package studybos.com.studybos2.data;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class RequestCode extends DataSupport implements Serializable{
	private int requestCode;
	public RequestCode() {
		// TODO Auto-generated constructor stub
	}
	public int getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}
}
