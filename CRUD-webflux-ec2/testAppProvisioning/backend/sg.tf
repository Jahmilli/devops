resource "aws_security_group" "app-sg" {
    name        = "${terraform.workspace}-TestAppSG"
    description = "Provide all the required rules to access and use the application"
    vpc_id      = "${data.aws_vpc.vpc_id.id}"   
}