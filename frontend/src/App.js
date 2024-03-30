import React, { useState } from 'react';
import axios from 'axios';

function App() {
  const [videoPaths, setVideoPaths] = useState({ youtube: '', youtubeShort: '' });

  const handleGenerateVideos = async () => {
    try {
      const response = await axios.get('/api/generateVideos');
      setVideoPaths(response.data);
    } catch (error) {
      console.error('Error generating videos:', error);
    }
  };

  return (
    <div className="App">
      <h1>Generate Videos</h1>
      <button onClick={handleGenerateVideos}>Get a new video</button>
      <div>
        <h2>Generated Videos:</h2>
        <div>
          <h3>YouTube Video:</h3>
          <video controls src={videoPaths.youtube} style={{ width: '100%' }}></video>
        </div>
        <div>
          <h3>YouTube Short/Instagram Reel Video:</h3>
          <video controls src={videoPaths.youtubeShort} style={{ width: '100%' }}></video>
        </div>
      </div>
    </div>
  );
}

export default App;
