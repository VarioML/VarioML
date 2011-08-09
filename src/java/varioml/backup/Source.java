package org.varioml.data;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;


@Order(elements={"name","url","dbXRef","comment"})
public class Source  {

	@Attribute(required=false)
	private String id;
	
	@Attribute(required=false)
	private Date date;	

	@Attribute(required=false) 
	private String uri;

	@Attribute(required=false) 
	private Float version;

	
	@Element(required=false)
	private String name ;

	@Element(required=false) 
	private String url;

	@ElementList(required=false,inline=true)
	private List<Contact> contact;

	@ElementList(required=false,inline=true)
	private List<Acknowledgement> acknowledgement;

	@ElementList(inline=true,required=false)
	private List<DbXref> db_xref;
	
	@ElementList(inline=true,required=false)
	private List<Comment> comment;

	
	public String getName() {
		return name;
	}
	


	public void setName(String name) {
		this.name = name;
	}


	public List<Contact> getContact() {
		return contact;
	}

	public void addContact( Contact c) {
		if ( contact == null ) { 
			contact = new ArrayList<Source.Contact>();
		}
		contact.add(c);
	}


	
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getUri() {
		return uri;
	}



	public void setUri(String uri) {
		this.uri = uri;
	}



	public Float getVersion() {
		return version;
	}



	public void setVersion(Float version) {
		this.version = version;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<Acknowledgement> getAcknowledgement() {
		return acknowledgement;
	}



	public void setAcknowledgement(List<Acknowledgement> acknowledgement) {
		this.acknowledgement = acknowledgement;
	}



	public List<DbXref> getDbXref() {
		return db_xref;
	}



	public void setDbXref(List<DbXref> dbXref) {
		this.db_xref = dbXref;
	}



	public List<Comment> getComment() {
		return comment;
	}



	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}



	public static class Acknowledgement extends AnnotatedElement{
		
		@Element(required=false)
		private String name;
		@Element(required=false)	
		private DbXref grant_number;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public DbXref getGrant_number() {
			return grant_number;
		}
		public void setGrant_number(DbXref grant_number) {
			this.grant_number = grant_number;
		}

		
	}

	public static class Contact extends AnnotatedElement { 
		
		@Element
		private String name;

		@ElementList(required=false,inline=true)
		private List<String> url;

		@Element(required=false)
		private String address;

		@Element(required=false)
		private String phone;

		@Element(required=false)
		private String fax;

		@Element(required=false)
		private String email;

		@Element(required=false)
		private String role;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public List<String> getUrl() {
			return url;
		}

		public void setUrl(List<String> url) {
			this.url = url;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
		
		
		
	}
}
