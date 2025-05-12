# OpenRouteService Railway Deployment for Bogota

This document provides instructions for deploying OpenRouteService on Railway with Bogota map data.

## Important Files

- `Dockerfile.railway`: A specialized Dockerfile that:
  1. Downloads Colombia OSM data
  2. Extracts the Bogota region using bounding box
  3. Places the Bogota data in the expected location for OpenRouteService
  4. Sets up both car and walking profiles

- `railway.json`: Configuration file that tells Railway to use the custom Dockerfile

## Deployment Steps

1. Connect your GitHub repository to Railway
2. Railway will use the custom `Dockerfile.railway` file as specified in `railway.json`
3. The deployment will automatically:
   - Download and extract Bogota OSM data
   - Save it with the expected filename (example-heidelberg.test.pbf)
   - Configure the profiles
   - Build the routing graph

4. No additional environment variables are needed, as they're set in the Dockerfile

## API Usage

The API endpoints for directions will be:

```
# For car routing
https://your-railway-url.railway.app/ors/v2/directions/driving-car/json

# For walking routing (either of these will work)
https://your-railway-url.railway.app/ors/v2/directions/walking/json
```

## API Examples

### Car routing example:

```
POST https://your-railway-url.railway.app/ors/v2/directions/driving-car/json

{
  "coordinates": [
    [-74.1, 4.6],  // Bogota coordinates
    [-74.05, 4.65]
  ]
}
```

### Walking routing example:

```
POST https://your-railway-url.railway.app/ors/v2/directions/walking/json

{
  "coordinates": [
    [-74.1, 4.6],  // Bogota coordinates 
    [-74.05, 4.65]
  ]
}
```

## Important Notes

- The first deployment will take some time as it needs to:
  1. Download the Colombia OSM data (~350MB)
  2. Extract the Bogota region
  3. Build the routing graphs
- Each enabled profile will build its own graph, which increases memory requirements
- The Bogota extract is based on the bounding box: -74.2,4.5,-74.0,4.8
- Although we're using Bogota data, the file is named example-heidelberg.test.pbf to match the expected configuration

## Troubleshooting

If you encounter issues:

1. Check the Railway logs to make sure the OSM file download and extraction completed successfully
2. Verify that the API endpoints are correct
3. The Dockerfile contains debug logging to help identify issues

## Bounding Box Information

The Bogota extract uses the following bounding box:
- Western boundary: -74.2° longitude
- Southern boundary: 4.5° latitude
- Eastern boundary: -74.0° longitude
- Northern boundary: 4.8° latitude

This covers the central area of Bogota and surrounding regions.

## Additional Configuration

For additional configuration options, please refer to the official OpenRouteService documentation:
https://giscience.github.io/openrouteservice/run-instance/running-with-docker 