package api.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.BookEndPoints;
import api.payload.Book;
import io.restassured.response.Response;

public class BookTests {

	Book bookPayload;

	@BeforeClass
	public void setupData()
	{

		bookPayload = new Book();

		bookPayload.setId(1);
		bookPayload.setTitle("Book 1");
		bookPayload.setAuthor("Person 1");
	}

	@Test(priority = 1)
	public void test_create_book_as_user()
	{
		Response response = BookEndPoints.createBookUser(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),201);
	}

	@Test(priority = 2)
	public void test_create_book_as_admin()
	{
		bookPayload.setId(2);
		bookPayload.setTitle("Book 2");
		bookPayload.setAuthor("Person 2");

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),201);
	}
	
	@Test(priority = 3)
	public void test_create_book_with_empty_title_as_admin()
	{
		bookPayload.setId(3);
		bookPayload.setTitle(null);
		bookPayload.setAuthor("Person 3");

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),400);
	}
	
	@Test(priority = 4)
	public void test_create_book_with_empty_title_as_user()
	{
		bookPayload.setId(4);
		bookPayload.setTitle(null);
		bookPayload.setAuthor("Person 4");

		Response response = BookEndPoints.createBookUser(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),400);
	}
	
	@Test(priority = 5)
	public void test_create_book_with_empty_author_as_user()
	{
		bookPayload.setId(5);
		bookPayload.setTitle("Book 3");
		bookPayload.setAuthor(null);

		Response response = BookEndPoints.createBookUser(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),400);
	}
	
	@Test(priority = 6)
	public void test_create_book_with_empty_author_as_admin()
	{
		bookPayload.setId(6);
		bookPayload.setTitle("Book 3");
		bookPayload.setAuthor(null);

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),400);
	}
	
	@Test(priority = 7)
	public void test_create_book_with_empty_author_and_title()
	{
		bookPayload.setId(6);
		bookPayload.setTitle("Book_1");
		bookPayload.setAuthor("");

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),400);
	}
	
	@Test(priority = 8)
	public void test_create_book_with_existing_title_as_admin()
	{
		bookPayload.setId(7);
		bookPayload.setTitle("Book 1");
		bookPayload.setAuthor(null);

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),208);
	}
	
	
	@Test(priority = 9)
    public void test_create_book_with_integer_value_for_author()
    {
        Response response = BookEndPoints.createBook(1,"Book 8");
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),400);
    }
	
	@Test(priority = 10)
    public void test_create_book_with_long_inputs()
    {
		bookPayload.setId(6);
		bookPayload.setTitle("Book_1 of trfhjkfea afhaojoa aajfoiajfiaj afjashfaoishh");
		bookPayload.setAuthor("sdadasd safsfafafasfasf");

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),201);
    }
	
	@Test(priority = 11)
    public void test_create_book_with_existing_title_altered_case()
    {
		bookPayload.setId(6);
		bookPayload.setTitle("book 1");
		bookPayload.setAuthor("person 11");

		Response response = BookEndPoints.createBookAdmin(bookPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(),201);
    }


}
