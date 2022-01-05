package com.example;

/**
 * @see https://fakerestapi.azurewebsites.net/index.html
 * @example
 * [
 *   {
 *     "id": 0, "idBook": 0, "firstName": "string",
 *     "lastName": "string"
 *   }
 * ]
 */
public class FakeRestApiAuthors {
  public int id;
  public int idBook;
  public String firstName;
  public String lastName;

  @Override
  public String toString() {
    return "FakeRestApiAuthors [firstName=" + firstName + ", id=" + id + ", idBook=" + idBook + ", lastName=" + lastName
        + "]";
  }
}
