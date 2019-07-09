resource "aws_security_group_rule" "app-ingress-http" {
    type            = "ingress"
    from_port       = 80
    to_port         = 80
    protocol        = "tcp"
    # Opening to 0.0.0.0/0 can lead to security vulnerabilities.
    cidr_blocks = ["0.0.0.0/0"] # Definitely fix this up

    security_group_id = "${aws_security_group.app-sg.id}"
}

resource "aws_security_group_rule" "app-egress-http" {
    type            = "egress"
    from_port       = 80
    to_port         = 80
    protocol        = "tcp"
    # Opening to 0.0.0.0/0 can lead to security vulnerabilities.
    cidr_blocks = ["0.0.0.0/0"] # Definitely fix this up

    security_group_id = "${aws_security_group.app-sg.id}"
}

resource "aws_security_group_rule" "app-ingress-https" {
    type            = "ingress"
    from_port       = 443
    to_port         = 443
    protocol        = "tcp"
    # Opening to 0.0.0.0/0 can lead to security vulnerabilities.
    cidr_blocks = ["0.0.0.0/0"] # Definitely fix this up

    security_group_id = "${aws_security_group.app-sg.id}"
}

resource "aws_security_group_rule" "app-egress-https" {
    type            = "egress"
    from_port       = 443
    to_port         = 443
    protocol        = "tcp"
    # Opening to 0.0.0.0/0 can lead to security vulnerabilities.
    cidr_blocks = ["0.0.0.0/0"] # Definitely fix this up

    security_group_id = "${aws_security_group.app-sg.id}"
}

resource "aws_security_group_rule" "app-ingress-ssh" {
    type            = "ingress"
    from_port       = 22
    to_port         = 22
    protocol        = "tcp"
    # Opening to 0.0.0.0/0 can lead to security vulnerabilities.
    cidr_blocks = ["0.0.0.0/0"] # Definitely fix this up

    security_group_id = "${aws_security_group.app-sg.id}"
}

resource "aws_security_group_rule" "app-egress-mongo" {
    type                     = "egress"
    from_port                = 41837
    to_port                  = 41837
    protocol                 = "tcp"
    cidr_blocks              = ["0.0.0.0/0"]
    
    security_group_id = "${aws_security_group.app-sg.id}"
}