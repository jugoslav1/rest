package rs.vicko.restserver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import rs.vicko.restserver.Main;
import static org.junit.Assert.assertEquals;

public class RestTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = Main.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(Main.BASE_URI);
	}

	@Test
	public void testHelloWorldPlain() {
		String responseMsg = target.path("hello").request(MediaType.TEXT_PLAIN)
				.get(String.class);
		assertEquals("Hello world", responseMsg);
	}

	@Test
	public void testHelloWorldXml() {
		String responseMsg = target.path("hello").request(MediaType.TEXT_XML)
				.get(String.class);
		assertEquals("<?xml version=\"1.0\"?><hello>Hello world</hello>",
				responseMsg);
	}

	@Test
	public void testHelloWorldHtml() {
		String responseMsg = target.path("hello").request(MediaType.TEXT_HTML)
				.get(String.class);
		assertEquals(
				"<html><title>Hello</title><body><h1>Hello world</body></h1></html>",
				responseMsg);
	}

	@Test
	public void testToDoXml() {
		String responseMsg = target.path("todo").request(MediaType.TEXT_XML)
				.get(String.class);
		assertEquals("test", responseMsg);
	}

	@Test
	public void testToDoAppJson() {
		String responseMsg = target.path("todo")
				.request(MediaType.APPLICATION_JSON).get(String.class);
		assertEquals("test", responseMsg);
	}

	@Test
	public void testToDoAppXml() {
		String responseMsg = target.path("todo")
				.request(MediaType.APPLICATION_XML).get(String.class);
		assertEquals("test", responseMsg);
	}

	@Test
	public void testToDoAppJsonObject() {
		ToDo responseMsg = target.path("todo")
				.request(MediaType.APPLICATION_JSON).get(ToDo.class);
		assertEquals("test", responseMsg);
	}

	
	@SuppressWarnings("deprecation")
	@After
	public void tearDown() throws Exception {
		server.stop();
	}

}
