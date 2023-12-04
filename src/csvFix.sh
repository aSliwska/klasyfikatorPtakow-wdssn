#!/bin/bash

cd ../dane
sed -i 's/PARAKETT  AKULET/PARAKEET AUKLET/g' birds.csv
sed -i 's/  / /g' birds.csv
sed -i 's/.0,/,/g' birds.csv