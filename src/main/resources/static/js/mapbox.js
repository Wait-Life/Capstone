"use strict";
$(document).ready(function() {
    // let mapBoxKey = $('#mapBoxKey').val();

    mapboxgl.accessToken = mapBoxKey;
    let eventLocation = $('#address').text();

    geocode(eventLocation, mapBoxKey).then(function(location) {
        let mapOptions = {
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v9',
            zoom: 15,
            showZoom: true,
            center: location
        };

        let map = new mapboxgl.Map(mapOptions);

        let marker = new mapboxgl.Marker()
            .setLngLat(location)
            .addTo(map);

        map.addControl(new mapboxgl.NavigationControl());

    });
});