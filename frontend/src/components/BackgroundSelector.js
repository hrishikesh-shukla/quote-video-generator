import React, { useState, useEffect } from 'react';
import { createClient } from 'pexels';
import axios from 'axios';

const BackgroundSelector = ({ onSelectBackground }) => {
    const [backgroundUrl, setBackgroundUrl] = useState('');
    const client = createClient('7qoGGiPkSprYDeRfdxkNgLIrawYlneyiFnFFEHTVDiab7TrzbZKOSTsF');
    const query = 'Nature';

    useEffect(() => {
        fetchBackground();
    }, []);

    const fetchBackground = async () => {
        try {
            const response = await axios.get('https://source.unsplash.com/1600x900/?nature');
            setBackgroundUrl(response.request.responseURL);
            onSelectBackground(response.request.responseURL);
        } catch (error) {
            console.error('Error fetching background:', error);
        }
    };

    return (
        <div>
            <h2>Select Background Image</h2>
            <p>Fetching a random background image...</p>
            <img src={backgroundUrl} alt="Background" style={{ width: '100%' }} />
        </div>
    );
};

export default BackgroundSelector;
