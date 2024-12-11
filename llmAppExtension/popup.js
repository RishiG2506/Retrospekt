// Receive data from the background script
chrome.storage.local.get('tweetData', (result) => {
    if (result.tweetData) {
      const tweetContent = document.getElementById('tweetContent');
      tweetContent.innerHTML = `
        <strong>Tweet URL:</strong> ${result.tweetData.tweet_url}<br><br>
        <strong>Author:</strong> ${result.tweetData.author_name}<br><br>
        <strong>Tweet Content:</strong><br>
        ${result.tweetData.text_content}
      `;
    }
  });
  