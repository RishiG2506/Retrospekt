// Listener for messages from the content script
chrome.runtime.onMessage.addListener((message) => {
  if (message.type === 'TWEET_DATA') {
    // Store data locally for the popup
    chrome.storage.local.set({ tweetData: message.data });

    // Prepare the API URL for your Spring Boot backend
    const apiUrl = 'http://localhost:8080/api/content'; // Replace with your Spring Boot API URL

    console.log("teri mummy");

    // Send POST request to Spring Boot backend
    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        url: message.data.tweet_url,
        author: message.data.author_name,
        content: message.data.text_content,
      }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Successfully posted to Spring Boot API:', data);
    })
    .catch(error => {
      console.error('Error posting to Spring Boot API:', error);
    });

    // Open the popup (optional, if you want to display the data in the popup)
    chrome.action.openPopup();
  }
});