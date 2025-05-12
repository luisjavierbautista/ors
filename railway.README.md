# OpenRouteService Railway Deployment

This document provides instructions for deploying OpenRouteService on Railway.

## Deployment Steps

1. Connect your GitHub repository to Railway
2. Railway will automatically use the Dockerfile in the repository root
3. Set the following environment variables in your Railway project:

```
REBUILD_GRAPHS=True

# Car profile
ors.engine.profiles.driving-car.enabled=true
ors.engine.profiles.driving-car.encoder_name=driving-car

# Walking profiles - configure both to support either URL
ors.engine.profiles.foot-walking.enabled=true
ors.engine.profiles.foot-walking.encoder_name=foot-walking
ors.engine.profiles.walking.enabled=true
ors.engine.profiles.walking.encoder_name=foot-walking
```

4. The service will be available at the URL provided by Railway once deployment is complete

## API Usage

The API endpoints for directions will be:

```
# For car routing
https://your-railway-url.railway.app/ors/v2/directions/driving-car/json

# For walking routing (either of these will work)
https://your-railway-url.railway.app/ors/v2/directions/foot-walking/json
https://your-railway-url.railway.app/ors/v2/directions/walking/json
```

IMPORTANT: Make sure to use the exact same profile names in your API calls as configured in your environment variables.

## API Examples

### Car routing example:

```
POST https://your-railway-url.railway.app/ors/v2/directions/driving-car/json

{
  "coordinates": [
    [8.681495, 49.41461],
    [8.686507, 49.41943]
  ]
}
```

### Walking routing example:

```
POST https://your-railway-url.railway.app/ors/v2/directions/walking/json

{
  "coordinates": [
    [8.681495, 49.41461],
    [8.686507, 49.41943]
  ]
}
```

## Important Notes

- The first deployment will take some time as it needs to build the routing graphs from the OpenStreetMap data
- Each enabled profile will build its own graph, which increases memory requirements
- The default OSM file used is the heidelberg.test.pbf file included in the repository
- To use a different OSM file, you'll need to modify the Dockerfile to include your own OSM file

## Troubleshooting

If you encounter the error "Parameter 'profile' has incorrect value of 'unknown'":
1. Make sure your API calls use the exact same profile name as configured (`driving-car`, `foot-walking`, or `walking`)
2. Verify that your configuration has both the profile enabled and the encoder name set correctly
3. The profile name in the URL must match the profile name in your configuration

If you see "Problem while parsing file" or "Expected single profile in config":
1. The OSM file might not exist or is corrupted
2. Your profile configuration might be incorrect
3. Check the container logs for more detailed error messages

## Additional Configuration

For additional configuration options, please refer to the official OpenRouteService documentation:
https://giscience.github.io/openrouteservice/run-instance/running-with-docker 