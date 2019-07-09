resource "aws_instance" "app" {
    ami             = "${var.ami_id}" # TODO: Bake our own AMI and use that
    instance_type   = "t2.micro"
    key_name        = "${var.key_pair_name}"
    security_groups = ["${aws_security_group.app-sg.name}"]

    tags = {
        Name        = "${terraform.workspace}-TestApp"
        Description = "A test application instance"
    }
}

output "app_ip_public_addr" {
    value       = "${aws_instance.app.public_ip}"
    description = "The private ip address of the application instance"
}

output "app_ip_private_addr" {
    value       = "${aws_instance.app.private_ip}"
    description = "The private ip address of the application instance"
}
