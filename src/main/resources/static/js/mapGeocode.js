"use strict";
$(document).ready(function() {
    mapboxgl.accessToken = mapBoxKey;

    geocode("San Antonio, TX", mapBoxKey).then(function(loc) {
        let mapOptions = {
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v9',
            zoom: 15,
            showZoom: true,
            center: loc
        };

        let map = new mapboxgl.Map(mapOptions);

        map.addControl(new mapboxgl.NavigationControl());

        let geocoder = new MapboxGeocoder({
            accessToken: mapBoxKey,
            mapboxgl: mapboxgl
        });

        document.getElementById('geocoder').appendChild(geocoder.onAdd(map));
        let search = $('.mapboxgl-ctrl-geocoder--input');
        search.attr('id', 'address');
        search.attr('name', 'address');
    });
});