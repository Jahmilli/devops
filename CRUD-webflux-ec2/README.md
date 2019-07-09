# CRUD Webflux EC2

## Description
This was developed to test building a CRUD application using Webflux with Springboot, a framework that is built
on the reactive programming model. I really enjoyed it finding it similar to NodeJS. Aside from just building 
the application, it contains the following:
- CI/CD using TravisCI
- Automatic deployments of build artifact into S3 for builds from master branch
- Automatic Infrastructure provisioning using Terraform
- Automatic deployments onto the EC2 instance, installing a number of dependencies, and pulling the JAR file
from S3 and then running it.

### TODO:
Additional improvements to this that could be done are:
- Automating the setup of Nginx which would then be run infront of the springboot application
- Setting up a TLS certificate where Nginx would then additionally be used for SSL offloading
- Setting up TravisCI to run the deployments of the JAR file onto the EC2 instance. This can be done by passing
in the AWS_ACCESS_KEY and AWS_SECRET_ACCESS_KEY in as environment variables. It would be good to figure
out how to get Travis to assume a predefined role containing all the necessary policies for provisioning and 
deployment.
