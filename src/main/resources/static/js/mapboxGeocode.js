"use strict";
$(document).ready(function() {
    // let mapBoxKey = $('#mapBoxKey').val();

    mapboxgl.accessToken = mapBoxKey;
    let eventLocation = $('#address').text();


    geocode("San Antonio, TX", mapBoxKey).then(function(location) {
        let mapOptions = {
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v9',
            zoom: 15,
            showZoom: true,
            center: location
        };

        let geocoder = new MapboxGeocoder({
            accessToken: mapBoxKey,
            mapboxgl: mapboxgl
        });

        let map = new mapboxgl.Map(mapOptions);

        let marker = new mapboxgl.Marker()
            .setLngLat(location)
            .addTo(map);

        map.addControl(new mapboxgl.NavigationControl());

        document.getElementById('geocoder').appendChild(geocoder.onAdd(map));
        let search = $('.mapboxgl-ctrl-geocoder--input');
        search.attr('id', 'address');
        search.attr('name', 'address');
    });
});