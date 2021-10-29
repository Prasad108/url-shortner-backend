# url-shortner-backend
The project does the job of shortening the URL and on request of short url it will redirect to original Long url

To start the project just run the main class

# Algorithm used
For every request we create a random string as short URL.
This short url is saved to in-memory database along with its long URL

#Retrival of longURL from short URL 
we take the shortUrl check if we have corresponding entry in db.
If we have then redirect to the respective long URL

## Logic for generating short key
We have used the Radome integer generator and based on that value we have converted it to String

#Future Scope
If URL mapping not found redirect to front ends Not-Found Page
