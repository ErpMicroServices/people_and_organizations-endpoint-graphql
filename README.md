# party party Services

## Build the docker image
'''
docker build --tag ems-party-party-service .
'''

## Run The Services

## Local/Dev
'''
docker run --detach --publish 8000:80 --name ems-party-party-service_1 -v $(pwd):/usr/src/app --link ems-party-db-1:erp-party-db erp-party-party-service
'''

## Staging or Prod
'''
docker run --detach --publish 8000:80 --name ems-party-party-service_1 -v $(pwd):/usr/src/app --link ems-party-db-1:erp-party-db ems-party-party-service
'''
