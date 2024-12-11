// Function to insert the button into each tweet
function insertButton() {
  const tweets = document.querySelectorAll('article'); // Adjust if needed
  tweets.forEach(tweet => {
    // Avoid adding multiple buttons to the same tweet
    if (!tweet.querySelector('.my-extension-button')) {
      const button = document.createElement('button');
      button.textContent = 'Save to Retrospekt';
      button.className = 'my-extension-button';
      button.style = 'position: absolute; top: 10px; right: 10px; z-index: 1000;';

      // Add click event listener
      button.addEventListener('click', () => {
        const tweetURL = window.location.href;

        // Extract tweet content
        const tweetContentElement = tweet.querySelector('div[data-testid="tweetText"]');
        const tweetContent = tweetContentElement ? tweetContentElement.innerHTML : 'No content';

        // Extract author name
        const authorElement = tweet.querySelector('div span.css-901oao span');  // Adjust this selector to match the structure for the author name
        const authorName = authorElement ? authorElement.textContent : 'Unknown Author';

        // Send data to background script
        chrome.runtime.sendMessage({
          type: 'TWEET_DATA',
          data: {
            tweet_url: tweetURL,
            author_name: authorName,
            text_content: tweetContent
          }
        });
      });

      tweet.appendChild(button);
    }
  });
}

// Observe changes to dynamically load the button when tweets are loaded
const observer = new MutationObserver(insertButton);
observer.observe(document.body, { childList: true, subtree: true });

// Initial button insertion
insertButton();
