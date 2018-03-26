package user.domain;

public class Blog {
	private int BlogId;
	 private int UserId;
	 private String BlogTitle;
	 private String BlogDescription;
	 private String BlogCatagory;
	 private String BlogTag;

	 public void setBlogId(int BlogId2) {
	  BlogId=BlogId2;
	 }

	 public int getBlogId() {
	  // TODO Auto-generated method stub
	  return BlogId;
	 }

	 public void setUserId(int UserId2) {
	  UserId=UserId2;
	 }

	 public int getUserId() {
	  // TODO Auto-generated method stub
	  return UserId;
	 }

	 public void setBlogTitle(String BlogT2) {
	  BlogTitle=BlogT2;
	 }

	 public String getBlogTitle() {
	  // TODO Auto-generated method stub
	  return BlogTitle;
	 }

	 public void setBlogDescription(String BlogDE) {
	  BlogDescription=BlogDE;
	 }

	 public String getBlogDescription() {
	  // TODO Auto-generated method stub
	  return BlogDescription;
	 }

	 public void setBlogCatagory(String BlogCata) {
	  BlogCatagory=BlogCata;
	 }

	 public String getBlogCatagory() {
	  // TODO Auto-generated method stub
	  return BlogCatagory;
	 }

	public void SetBlogTag(String blogtag) {
		// TODO Auto-generated method stub
		BlogTag = blogtag;
	}

	public String getBlogTag() {
		// TODO Auto-generated method stub
		return BlogTag;
	}
}
